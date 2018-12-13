package my.company.api.client;

import my.company.api.model.StatusEnum;

public class StatusEnumExpander implements feign.Param.Expander {

    @Override
    public String expand(Object o) {
        if (o instanceof StatusEnum) {
            return o.toString();
        }
        throw new IllegalArgumentException("Must provide object to type " + StatusEnum.class);
    }

}
