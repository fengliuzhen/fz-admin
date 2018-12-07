public class Metting {
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int startTime;
    public int endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;

    public Metting(int id,int startTime,int endTime)
    {
        this.id=id;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
