package ua.training;

public class Payroll {
    private PayrollTypes type;
    private int salaryTotal;

    public Payroll(int salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    public Payroll() {
    }

    void setPayrollType(PayrollTypes payrollType) {
        this.type = payrollType;
    }

    void setSalaryTotal(int salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    void getSalaryTotal(int salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    int getSalary(Department department, Employee e) {
        int salary = 0;
        int remainder = this.salaryTotal - department.getDepartmentSalary();

        switch (type) {
            case EQUAL:
                salary = e.getSalary() + (int) Math.floor(remainder / department.getEmployeesCount());
                break;
            case PROPORTIONAL:
                salary = e.getSalary() + (int) Math.floor(remainder / department.getEmployeesCount());
                break;
            default:
                break;
        }

        return salary;
    }
}
