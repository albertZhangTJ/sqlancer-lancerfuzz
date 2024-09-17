//this is the IR for @X notations
//these nodes will be worked into the parent node during post processing
//thus the renderer should not see this
public class ScheduleNode extends Node{
    private int priority;
    public ScheduleNode(int priority){
        this.priority = priority;
    }
    public int get_priority(){
        return this.priority;
    }
}
