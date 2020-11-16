/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_6;

/**
 *
 * @author Tamara
 */
public class PrioritisedOutput implements Comparable<PrioritisedOutput> {

    private int prioity;
    private String result;

    public PrioritisedOutput(int prioity, String result) {
        this.prioity = prioity;
        this.result = result;
    }

    @Override
    public int compareTo(PrioritisedOutput o) {
        return this.prioity - o.prioity;
    }

    public int getPrioity() {
        return prioity;
    }

    public String getResult() {
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.prioity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrioritisedOutput other = (PrioritisedOutput) obj;
        if (this.prioity != other.prioity) {
            return false;
        }
        return true;
    }
}
