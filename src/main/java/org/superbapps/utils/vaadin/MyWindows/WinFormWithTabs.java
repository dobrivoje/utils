package org.superbapps.utils.vaadin.MyWindows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import static org.superbapps.utils.vaadin.Views.View_Dashboard.NotificationsButton.ID;

/**
 *
 * @author д06ри
 */
public class WinFormWithTabs extends Window {

    //<editor-fold defaultstate="collapsed" desc="properties">
    protected TabSheet detailsWrapper = new TabSheet();
    protected VerticalLayout content = new VerticalLayout();

    protected Button actionButton;
    protected Button closeButton;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Konstruktori">
    public WinFormWithTabs(String caption, Layout formLayout, int winHeight, int winWidth, Unit winUnit,
            String actionButtonCaption, Button.ClickListener externalButtonClickListener,
            String imgLocation, int imgHeight, int imgWidth, boolean readOnly) {

        createMainWindow(winHeight, winWidth, winUnit, actionButtonCaption, externalButtonClickListener, readOnly,
                createTabWithImageAndForm(caption, formLayout, imgLocation, imgHeight, imgWidth, readOnly));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Infra">
    private void createMainWindow(int winHeight, int winWidth, Unit winUnit,
            String actionButtonCaption, Button.ClickListener externalButtonClickListener,
            boolean readOnly, Component tabComponent) {

        addStyleName("profile-window");
        setId(ID);
        Responsive.makeResponsive(this);

        setModal(true);
        addCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        setHeight(winHeight, winUnit);
        setWidth(winWidth, winUnit);

        if (actionButtonCaption != null && !actionButtonCaption.isEmpty()) {
            actionButton = new Button(actionButtonCaption);
            actionButton.setEnabled(!readOnly);
        }

        this.closeButton = new Button("Close");

        content.setSizeFull();
        content.setMargin(new MarginInfo(true, false, true, false));
        setContent(content);

        detailsWrapper.setSizeFull();
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);

        content.addComponent(detailsWrapper);
        content.setExpandRatio(detailsWrapper, 1f);

        detailsWrapper.addComponent(tabComponent);
        content.addComponent(buildFooter(externalButtonClickListener));
    }

    private Component createTabWithImageAndForm(String caption, Layout formLayout,
            String imageLocation, int imageHeight, int imageWidth, boolean readOnly) {

        if (formLayout == null) {
            formLayout = new VerticalLayout();
        }
        
        formLayout.setEnabled(!readOnly);
        formLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        formLayout.setSizeUndefined();

        HorizontalLayout centralLayout = new HorizontalLayout();
        centralLayout.setCaption(caption);
        centralLayout.setIcon(FontAwesome.USER);
        centralLayout.setSpacing(true);
        centralLayout.setMargin(true);
        centralLayout.addStyleName("profile-form");

        VerticalLayout picLayout = new VerticalLayout();
        picLayout.setSizeFull();
        picLayout.setSpacing(true);

        if (imageLocation == null) {
            imageLocation = "img/profile-pic-300px.jpg";
        }

        Image profilePic = new Image(null, new ThemeResource(imageLocation));

        if (imageWidth < 0 && imageHeight < 0) {
            profilePic.setWidth(85, Unit.PERCENTAGE);
            profilePic.setHeight(85, Unit.PERCENTAGE);
        } else if (imageWidth > 0 && imageHeight > 0) {
            profilePic.setWidth(imageWidth, Unit.PIXELS);
            profilePic.setHeight(imageHeight, Unit.PIXELS);
        } else {
            throw new RuntimeException("Nepostojeće dimenzije !");
        }

        picLayout.addComponent(profilePic);

        centralLayout.addComponent(picLayout);
        centralLayout.addComponent(formLayout);
        centralLayout.setExpandRatio(formLayout, 1);

        return centralLayout;
    }

    //<editor-fold defaultstate="collapsed" desc="Footer">
    private Component buildFooter(Button.ClickListener externalButtonClickListener) {
        HorizontalLayout footerLayout = new HorizontalLayout();

        footerLayout.setSpacing(true);
        footerLayout.setMargin(true);
        footerLayout.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footerLayout.setWidth(100, Unit.PERCENTAGE);

        closeButton.setWidth(150, Unit.PIXELS);
        closeButton.addStyleName(ValoTheme.BUTTON_DANGER);
        closeButton.addClickListener((Button.ClickEvent event) -> {
            close();
        });
        closeButton.focus();

        actionButton.setWidth(150, Unit.PIXELS);
        if (externalButtonClickListener != null) {
            actionButton.addClickListener(externalButtonClickListener);
        }

        footerLayout.addComponent(actionButton);
        footerLayout.addComponent(closeButton);

        footerLayout.setExpandRatio(actionButton, 1.0f);

        footerLayout.setComponentAlignment(actionButton, Alignment.MIDDLE_RIGHT);
        footerLayout.setComponentAlignment(closeButton, Alignment.MIDDLE_RIGHT);

        return footerLayout;
    }
    //</editor-fold>
    //</editor-fold>

    public void addTab(String caption, Layout formLayout, String imageLocation,
            int imageHeight, int imageWidth, boolean readOnly) {

        detailsWrapper.addComponent(
                createTabWithImageAndForm(caption, formLayout, imageLocation, imageHeight, imageWidth, readOnly)
        );
    }

    public WinFormWithTabs addNewTab(String caption, Layout formLayout, String imageLocation,
            int imageHeight, int imageWidth, boolean readOnly) {

        detailsWrapper.addComponent(
                createTabWithImageAndForm(caption, formLayout, imageLocation, imageHeight, imageWidth, readOnly)
        );

        return this;
    }
}
