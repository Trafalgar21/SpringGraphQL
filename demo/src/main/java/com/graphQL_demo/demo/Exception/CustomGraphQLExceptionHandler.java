package com.graphQL_demo.demo.Exception;


import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CustomGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        // Handle specific exceptions
        if (ex instanceof MovieNotFoundException) {
            return new GraphQLError() {
                @Override
                public String getMessage() {
                    return ex.getMessage();  // You can return a more user-friendly message
                }

                @Override
                public List<SourceLocation> getLocations() {
                    return null;  // You can add locations if needed
                }

                @Override
                public ErrorClassification getErrorType() {
                    return new MovieNotFoundException();
                }

                @Override
                public Map<String, Object> getExtensions() {
                    return Map.of("errorCode", "MOVIE_NOT_FOUND", "details", ex.getMessage());
                }
            };
        }

        if (ex instanceof ActorNotFoundException) {
            return new GraphQLError() {
                @Override
                public String getMessage() {
                    return ex.getMessage();  // You can return a more user-friendly message
                }

                @Override
                public List<SourceLocation> getLocations() {
                    return null;  // You can add locations if needed
                }

                @Override
                public ErrorClassification getErrorType() {
                    return new ActorNotFoundException();
                }

                @Override
                public Map<String, Object> getExtensions() {
                    return Map.of("errorCode", "ACTOR_NOT_FOUND", "details", ex.getMessage());
                }
            };
        }


        return super.resolveToSingleError(ex, env);
    }


}
