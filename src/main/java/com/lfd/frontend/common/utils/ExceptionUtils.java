package com.lfd.frontend.common.utils;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/**
 * Created by Ryan on 2017/6/29.
 */
public class ExceptionUtils {
    public static String getExceptionInfo(Exception e) {
        StringBuilder builder = new StringBuilder();

        Set<Throwable> dejaVu = Collections.newSetFromMap(new IdentityHashMap<Throwable, Boolean>());
        dejaVu.add(e);

        builder.append(e.toString());

        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement traceElement : trace)
            builder.append("\tat " + traceElement);

        // Print suppressed exceptions, if any
        for (Throwable se : e.getSuppressed())
            printEnclosedStackTrace(se, builder, trace, "Suppressed: ", "\t", dejaVu);

        // Print cause, if any
        Throwable ourCause = e.getCause();
        if (ourCause != null)
            printEnclosedStackTrace(ourCause, builder, trace, "Caused by: ", "", dejaVu);

        return builder.toString();
    }

    private static void printEnclosedStackTrace(Throwable t, StringBuilder builder, StackTraceElement[] enclosingTrace, String caption,
                                         String prefix, Set<Throwable> dejaVu) {
        if (dejaVu.contains(t)) {
            builder.append("\t[CIRCULAR REFERENCE:" + t + "]");
        } else {
            dejaVu.add(t);
            // Compute number of frames in common between this and enclosing
            // trace
            StackTraceElement[] trace = t.getStackTrace();
            int m = trace.length - 1;
            int n = enclosingTrace.length - 1;
            while (m >= 0 && n >= 0 && trace[m].equals(enclosingTrace[n])) {
                m--;
                n--;
            }
            int framesInCommon = trace.length - 1 - m;

            // Print our stack trace
            builder.append(prefix + caption + t);
            for (int i = 0; i <= m; i++)
                builder.append(prefix + "\tat " + trace[i]);
            if (framesInCommon != 0)
                builder.append(prefix + "\t... " + framesInCommon + " more");

            // Print suppressed exceptions, if any
            for (Throwable se : t.getSuppressed())
                printEnclosedStackTrace(se, builder, trace, "Suppressed: ", prefix + "\t", dejaVu);

            // Print cause, if any
            Throwable ourCause = t.getCause();
            if (ourCause != null)
                printEnclosedStackTrace(ourCause, builder, trace, "Caused by: ", prefix, dejaVu);
        }
    }
}
