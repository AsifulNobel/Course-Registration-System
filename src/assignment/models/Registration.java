package assignment.models;

import assignment.discountstrategies.IDiscountStrategy;

import java.util.LinkedList;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 * Created by nobel
 * modified by shawon
 * on 03/03/17.
 */
public class Registration extends Observable {
    public LinkedList<Course> courseList;
    private IExtraFeeCalculator iefc;
    private IDiscountStrategy discountStrategy;
    private int regId;
    private int totalCredits;
    private int discount;
    private int discountedTotal;

    public Registration() {
         courseList = new LinkedList<>();
    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourse(Course newCourse) {
        courseList.add(newCourse);
        totalCredits += newCourse.getCredit();
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public void setRegId(int id) {
        this.regId = id;
    }

    public int getRegId() {
        return this.regId;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setDiscountedTotal(int discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public int getTotal() {
        int total = courseList.stream().mapToInt(Course::getSubTotal).sum();

        return total;
    }

    public int getExtraFeeAmount() {
        iefc = CourseFactory.getInstance().getExtraFeeCalculator();

        return iefc.getExtraAmount(this.getTotal());
    }

    public int getGrandTotal() {
        return this.getTotal() + this.getExtraFeeAmount();
    }

    public int getDiscount() {
        if (discountStrategy != null)
            discount = this.getGrandTotal() - this.getDiscountedGrandTotal();
        return discount;
    }

    public int getDiscountedGrandTotal() {
        if (discountStrategy != null)
            discountedTotal = discountStrategy.getTotal(this) + this.getExtraFeeAmount();

        return discountedTotal;
    }

    public IDiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;

        setChanged();
        notifyObservers(this);
    }
}
