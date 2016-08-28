package org.superbapps.utils.vaadin.messages;

import com.vaadin.ui.Notification;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class VaadinMessageHolder {

    private String afterCommandExecutedMessage;
    private String messageTitle;
    private String messageContent;
    private Notification.Type messageType;

    public VaadinMessageHolder(String afterCommandExecutedMessage, String messageTitle, String messageContent, Notification.Type messageType) {
        this.afterCommandExecutedMessage = afterCommandExecutedMessage;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
        this.messageType = messageType;
    }

    public String getAfterCommandExecutedMessage() {
        return afterCommandExecutedMessage;
    }

    public void setAfterCommandExecutedMessage(String afterCommandExecutedMessage) {
        this.afterCommandExecutedMessage = afterCommandExecutedMessage;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Notification.Type getMessageType() {
        return messageType;
    }

    public void setMessageType(Notification.Type messageType) {
        this.messageType = messageType;
    }

}
