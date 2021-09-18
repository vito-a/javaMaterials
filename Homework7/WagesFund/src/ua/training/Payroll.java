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
        int departmentSalary = department.getDepartmentSalary();
        int remainder = this.salaryTotal - departmentSalary;

        switch (type) {
            case EQUAL:
                salary = e.getSalary() + (int) Math.floor(remainder / department.getEmployeesCount());
                break;
            case PROPORTIONAL:
                salary = e.getSalary() + (int) Math.floor(e.getSalary() * (departmentSalary / remainder));
                break;
            default:
                break;
        }

        return salary;
    }
}
