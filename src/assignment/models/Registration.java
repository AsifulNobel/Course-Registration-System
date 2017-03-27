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
    private int total;
    private int grandTotal;
    private IDiscountStrategy discountStrategy;

    public Registration() {
         courseList = new LinkedList<>();
         total = 0;
         grandTotal = 0;
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
        if(discountStrategy == null) {
            total = courseList.stream().mapToInt(Course::getSubTotal).sum();
        } else {
            total = discountStrategy.getTotal(this);
        }
        return total;
    }

    public int calculateGrandTotal() {
        int gT = this.getTotal() + this.getExtraFeeAmount();
        return gT;
    }

    public int getGrandTotal() {
        int gT = calculateGrandTotal();

        if(gT != grandTotal) {
            setChanged();
            notifyObservers();

            grandTotal = gT;
        }

        return grandTotal;
    }


    public int getExtraFeeAmount() {
        iefc = CourseFactory.getInstance().getExtraFeeCalculator();
        return iefc.getExtraAmount(this.getTotal());
    }

    public IDiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

}
