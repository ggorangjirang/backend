package com.elice.ggorangjirang.global.constant;


public class GlobalConstants {
    // 카테고리 관련 상수
    public static final String CATEGORY_ADD_SUCCESS_LOG = "Category added successfully with name: ";
    public static final String CATEGORY_EDIT_SUCCESS_LOG = "Category updated successfully with name: ";
    public static final String CATEGORY_DELETE_LOG = "Category deleting with name: ";


    // 서브 카테고리 관련 상수
    public static final String SUBCATEGORY_ADD_SUCCESS_LOG = "Subcategory added successfully with name: ";
    public static final String SUBCATEGORY_EDIT_SUCCESS_LOG = "Subcategory updated successfully with name: ";
    public static final String SUBCATEGORY_DELETE_LOG = "Subcategory deleting with name: ";


    // 상품 관련 상수
    public static final String PRODUCT_ADD_SUCCESS_LOG = "Product added successfully with id: ";
    public static final String PRODUCT_EDIT_SUCCESS_LOG = "Product updated successfully with id: ";
    public static final String PRODUCT_DELETE_LOG = "Product deleting with id: ";
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with id: ";
    public static final String REVIEW_NOT_FOUND_MESSAGE = "Review not found with id: ";
    public static final String HAS_PURCHASED_MESSAGE = "There is no purchase history for this product.";
    public static final String INVALID_PRODUCT_MESSAGE = "Price or Stock cannot be negative.";
    public static final String SUBCATEGORY_NOT_FOUND_MESSAGE = "Subcategory not found with id: ";
    public static final String NEW_PRODUCT_NOTICE = "New Product has created: ";
    public static final String PRODUCT_CHANGED_NOTICE = "Product has changed. Check the details in logs: ";
    public static final String PRODUCT_DELETED_NOTICE = "Product has deleted: ";
    public static final String PRODUCT_STOCK_NOTICE = " has less than 5 items left in stock.";


    // 배송 관련 상수
    public static final String DELIVERY_COMPLETE_EMAIL_TITLE = "[꼬랑지랑] 주문하신 상품이 배송 완료되었습니다. ";
    public static final String DELIVERY_COMPLETE_EMAIL_CONTENT= "주문하신 상품의 배송이 완료되었습니다. \n주문번호: ";


    // 주문 관련 상수
    public static final String ORDER_COMPLETE_EMAIL_TITLE = "[꼬랑지랑] 주문하신 상품의 결제가 완료되었습니다. ";
    public static final String ORDER_COMPLETE_EMAIL_CONTENT= "주문하신 상품의 결제가 완료되었습니다. \n주문번호: ";
    public static final String NEW_ORDER_NOTICE = "New Orders has created: ";


    // 리뷰 관련 상수
    public static final String REVIEW_ADD_SUCCESS_LOG = "Review added successfully by email: {}";
    public static final String REVIEW_EDIT_SUCCESS_LOG = "Review updated successfully by email: {}, reviewId: {}";
    public static final String REVIEW_DELETE_LOG = "Review deleting by email: {}, reviewId: {}";
}
