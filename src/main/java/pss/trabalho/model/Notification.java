package pss.trabalho.model;

import java.util.UUID;

public class Notification {
    private final UUID id;
    private final String message;
    private boolean read;
    private final UUID to;

    public Notification(UUID id, String message, UUID to, boolean read) {
        this.id = id;
        this.message = message;
        this.to = to;
        this.read = read;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public UUID getTo() {
        return to;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
