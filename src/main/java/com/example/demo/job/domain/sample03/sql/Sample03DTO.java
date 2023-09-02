package com.example.demo.job.domain.sample03.sql;

import lombok.Data;

@Data
public class Sample03DTO {
    
    /** ID */
    private long id;

    /** 名前 */
    private String name;

    /** 生年月日_年 */
    private int birth_year;

    /** 生年月日_月 */
    private int birth_month;

    /** 生年月日_日 */
    private int birth_day;

    /** 性別 */
    private String sex;

    /** 備考 */
    private String memo;

}
