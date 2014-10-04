package com.sevenklick.common.util.helpers;


import com.sevenklick.common.util.domain.Context;

/**
 * this class acts as a container to our thread local variables.
 * @author Pierre Petersson
 *
 */
public class ContextHandler {

    public static final ThreadLocal<Context> contextThreadLocal = new ThreadLocal<Context>();

    public static void set(Context user) {
        contextThreadLocal.set(user);
    }

    public static void unset() {
        contextThreadLocal.remove();
    }

    public static Context get() {
        return contextThreadLocal.get();
    }
}