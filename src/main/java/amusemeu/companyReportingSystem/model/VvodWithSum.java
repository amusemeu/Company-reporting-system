package amusemeu.companyReportingSystem.model;

public class VvodWithSum {
    private String nteName;
    private double planVvoda;
    private double factVvoda;
    private double procent;

    public String getNteName() {
        return nteName;
    }

    public void setNteName(String nteName) {
        this.nteName = nteName;
    }

    public double getPlanVvoda() {
        return planVvoda;
    }

    public void setPlanVvoda(int planVvoda) {
        this.planVvoda = planVvoda;
    }

    public double getFactVvoda() {
        return factVvoda;
    }

    public void setFactVvoda(int factVvoda) {
        this.factVvoda = factVvoda;
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }

    public  VvodWithSum(){}

   public VvodWithSum(String nteName, double planVvoda, double factVvoda, double procent) {
        this.nteName = nteName;
        this.planVvoda = planVvoda;
        this.factVvoda = factVvoda;
        this.procent = procent;
    }
}
