//this is the IR for @X notations
// during IR processing, all nodes with this expansion priority will be moved
// to be a child of this node
// the priority of the a default Node is 65536
// if any node has a ScheduleNode as child
// all its children must be ScheduleNodes
public class ScheduleNode extends Node{
    private int priority;
    public ScheduleNode(int priority){
        this.priority = priority;
    }
    public int get_priority(){
        return this.priority;
    }

    @Override
    public String render(){
        
    }
}
