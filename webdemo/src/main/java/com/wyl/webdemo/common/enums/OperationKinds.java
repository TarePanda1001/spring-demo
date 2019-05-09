package com.wyl.webdemo.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum OperationKinds {
    QUERY(0, "查询"),
    ADD(1, "添加"),
    EDIT(2, "编辑"),
    UPDATE_STUTAS(3, "更新状态"),
    DELETE(4, "删除"),
    ;
    private Integer code;
    private String desc;

    OperationKinds(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getAction(Integer code){
        for(OperationKinds action : OperationKinds.values()){
            if(action.getCode().equals(code)){
                return action.getDesc();
            }
        }
        return StringUtils.EMPTY;
    }

//    QUERY_STUDENT_INFO("student_info", 1, "查询学生信息", "student_id"),
//    DELETE_STUDENT("student_info", 2, "删除学生", "student_id"),
//    ADD_STUDENT("student_info", 3, "添加学生", "student_id"),
//    EDIT_STUDENT("student_info", 4, "编辑学生", "student_id");
//    ;
//
//    private String module;
//    private int type;
//    private String operation;
//    private String searchWord;
//
//    OperationKinds(String module, int type, String operation, String searchWord) {
//        this.module = module;
//        this.type = type;
//        this.operation = operation;
//        this.searchWord = searchWord;
//    }
//
//    public String getModule() {
//        return module;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public String getOperation() {
//        return operation;
//    }
//
//    public String getSearchWord() {
//        return searchWord;
//    }
}
