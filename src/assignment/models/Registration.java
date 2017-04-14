package assignment.models;

import assignment.discountstrategies.IDiscountStrategy;

import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by nobel
 * modified by shawon
 * on 03/03/17.
 */
public class Registration extends Observable {
    public LinkedList<Course> courseList;
    private IExtraFeeCalculator iefc;
    private IDiscountStrategy discountStrategy;

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
        return this.getGrandTotal() - this.getDiscountedGrandTotal();
    }

    public int getDiscountedGrandTotal() {
        return discountStrategy.getTotal(this) + this.getExtraFeeAmount();
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
