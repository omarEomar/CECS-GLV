package com.beshara.jsfbase.csc.util.wizardbar2.state;

import java.io.Serializable;


public class WizardState implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private boolean visited;
    private boolean validated;

    public WizardState() {

    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean isValidated() {
        return validated;
    }
}
