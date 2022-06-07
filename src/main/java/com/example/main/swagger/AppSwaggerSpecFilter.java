package com.example.main.swagger;

import io.swagger.core.filter.SwaggerSpecFilter;
import io.swagger.model.ApiDescription;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;
import java.util.List;
import java.util.Map;

public class AppSwaggerSpecFilter implements SwaggerSpecFilter
{
    public static final String INTERNAL_PARAMETER = "internal";

    @Override
    public boolean isOperationAllowed(Operation oprtn, ApiDescription ad, Map<String, List<String>> map, Map<String, String> map1, Map<String, List<String>> map2)
    {
        return true;
    }

    @Override
    public boolean isParamAllowed(Parameter parameter, Operation oprtn, ApiDescription ad, Map<String, List<String>> map, Map<String, String> map1, Map<String, List<String>> map2)
    {
        if (parameter.getAccess() == null)
            return true;

        boolean isInternal = (!parameter.getAccess().isEmpty() && parameter.getAccess().contentEquals(INTERNAL_PARAMETER));

        return !isInternal;
    }

    @Override
    public boolean isPropertyAllowed(Model model, Property prprt, String string, Map<String, List<String>> map, Map<String, String> map1, Map<String, List<String>> map2)
    {
        return true;
    }

}