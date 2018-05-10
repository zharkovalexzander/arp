package ctos.iot.structure;

import org.springframework.stereotype.Component;

@Component
public class Controller extends ModuleRouter {
    public Controller() {
        super();
        this.id = "1";
    }

    @Override
    public String moduleName() {
        return "cont";
    }
}
