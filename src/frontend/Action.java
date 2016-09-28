package frontend;

import java.util.List;

/**
 * Created by Joseph on 14/09/2016.
 */
public class Action {
    private String action;
    private List<String> parameters;
     public Action(String action, List<String> parameters){
         this.action = action;
         this.parameters = parameters;
     }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}
