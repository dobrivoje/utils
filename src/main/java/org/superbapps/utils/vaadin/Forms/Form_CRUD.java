package org.superbapps.utils.vaadin.Forms;

import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import static org.superbapps.utils.common.Enums.CrudOperations.BUTTON_CAPTION_SAVE;
import static org.superbapps.utils.common.Enums.ErrorMessages.FIELD_NOT_EMPTY_ERROR_MSG;
import org.superbapps.utils.vaadin.Tables.IRefreshVisualContainer;

public abstract class Form_CRUD<T> extends FormLayout {

    protected FieldGroup fieldGroup;
    protected BeanItem<T> beanItem;

    protected Button formButton;
    protected String formButtonCaption;
    protected Button.ClickListener clickListener;

    protected Form_CRUD() {
        setSizeFull();
        setMargin(true);
    }

    public Form_CRUD(final T bean, final IFormNotification notification) {
        this();

        // PAZI : OBAVEZNO INICIJALOIZOVATI U IZVEDENOJ KLASI !!!
        // fieldGroup.bindMemberFields(this);
        formButtonCaption = BUTTON_CAPTION_SAVE.toString();

        clickListener = new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                bindFieldsToBean(bean);

                try {
                    addNewBean(bean);
                    Notification.show("Novi " + notification.getNotification() + " Dodat.", Notification.Type.TRAY_NOTIFICATION);
                } catch (Exception ex) {
                    Notification.show("Greška", FIELD_NOT_EMPTY_ERROR_MSG.toString(), Notification.Type.ERROR_MESSAGE);
                }
            }
        };

        formButton = new Button(formButtonCaption, clickListener);

        // Moramo obavezno dodati u izvedenom konstruktoru
        // addComponents(field0, field1,...,fieldN, formButton);
    }

    public Form_CRUD(final T bean, final IRefreshVisualContainer visualContainer) {
        this();

        // PAZI : OBAVEZNO INICIJALOIZOVATI U IZVEDENOJ KLASI !!!
        fieldGroup.bindMemberFields(this);

        fieldGroup.setItemDataSource(new BeanItem(bean));
        beanItem = (BeanItem<T>) fieldGroup.getItemDataSource();

        formButtonCaption = BUTTON_CAPTION_SAVE.toString();

        clickListener = new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                T beanToUpdate = beanItem.getBean();
                bindFieldsToBean(beanToUpdate);

                try {
                    updateExistingBean(bean);

                    if (visualContainer != null) {
                        visualContainer.refreshVisualContainer();
                    }

                    Notification.show("Podatak ažuriran.", Notification.Type.TRAY_NOTIFICATION);
                } catch (Exception ex) {
                    Notification.show("Greška", ex.toString(), Notification.Type.ERROR_MESSAGE);
                }
            }
        };

        formButton = new Button(formButtonCaption, clickListener);

        // Moramo obavezno dodati u izvedenom konstruktoru
        // addComponents(field0, field1,...,fieldN, formButton);
    }

    protected abstract void bindFieldsToBean(T bean);

    public abstract void addNewBean(T bean) throws Exception;

    public abstract void updateExistingBean(T bean) throws Exception;
}
