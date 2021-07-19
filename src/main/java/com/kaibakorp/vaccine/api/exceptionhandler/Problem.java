package com.kaibakorp.vaccine.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private Integer status;
    private OffsetDateTime dateHour;
    private String title;
    private List<Field> fields;

    public Problem() {
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public static class Field{
        private String name;
        private String msg;

        public Field(String name, String msg){
            super();
            this.name=name;
            this.msg=msg;

        }

        public String getName() {
            return name;
        }

        public void setName(String nome) {
            this.name = nome;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(OffsetDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}