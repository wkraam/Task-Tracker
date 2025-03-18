import java.time.LocalDateTime;

public class Task{
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    public String toJSON() {
        return "{" +
                "\"id\":\"" + id + "\""+
                ", \"description\":\"" + description  +"\""+
                ", \"status\":\"" + status +"\""+
                ", \"createdAt\":\"" + createdAt +"\""+
                ", \"updatedAt\":\"" + updatedAt +"\""+
                '}';
    }

    //status can only be todo, in-progress, done
    public void setStatus(String status) {

        switch (status.toLowerCase()) {
            case "todo" -> this.status = Status.TODO;
            case "in-progress", "in progress" -> this.status = Status.IN_PROGRESS;
            case "done" -> this.status = Status.DONE;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Task(int id, String description, Status status, LocalDateTime creationTime, LocalDateTime updatedTime) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = creationTime;
        this.updatedAt = updatedTime;
    }
}
