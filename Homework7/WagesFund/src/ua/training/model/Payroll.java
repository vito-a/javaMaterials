package ua.training.model;

public class Payroll {
    private PayrollTypes type;
    private long salaryTotal;

    public Payroll(long salaryTotal, PayrollTypes payrollType) {
        this.salaryTotal = salaryTotal;
        this.type = payrollType;
    }

    public Payroll(int salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    public Payroll() {
    }

    public void setPayrollType(PayrollTypes payrollType) {
        this.type = payrollType;
    }

    void setSalaryTotal(long salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    void getSalaryTotal(long salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    public long getSalary(Department department, Employee e) throws IllegalArgumentException {
        if (salaryTotal <= 0 ) {
           throw new IllegalArgumentException("The total salary for the '" + department.getDepartmentName() + "' department is incorrect.");
        }
        if (type == null) {
            throw new IllegalArgumentException("The payroll type for the '" + department.getDepartmentName() + "' department is incorrect.");
        }

        long salary = 0;
        long baseSalary = department.getBaseSalary();
        long remainder = this.salaryTotal - baseSalary;
        double proportion;

        switch (type) {
            case EQUAL:
                salary = e.getSalary() + (int) remainder / department.getEmployeesCount();
                break;
            case PROPORTIONAL:
                proportion = (double) remainder / baseSalary;
                salary = e.getSalary() + Math.round(e.getSalary() * proportion);
                break;
            default:
                break;
        }

        return salary;
    }
}
