package com.roo.model.param;

import lombok.*;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SearchParam extends PageParam {

    private String q;

}
