public class Patient implements ComparableContent<Patient> {
  
  private int alter;
  private String name;
  
  public Patient(int pAlter, String pName) {
    this.alter = pAlter;
    this.name = pName;
  }

  public String toString() {
    return name + " (" + alter + ")";
  }

  public int getAlter() {
    return alter;
  }

  public String getName() {
    return name;
  }

  public boolean isEqual(Patient pAlter) {
    if (alter == pAlter.getAlter()){
      return true;
    }
    return false;
  }

  public boolean isGreater(Patient pAlter) {
    if (alter > pAlter.getAlter()){
      return true;
    }
    return false;
  }

  public boolean isLess(Patient pAlter) {
    if (alter < pAlter.getAlter()){
      return true;
    }
    return false;
  }

}

