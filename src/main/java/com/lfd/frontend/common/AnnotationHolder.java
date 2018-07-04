package com.lfd.frontend.common;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ryan on 2/8/17.
 */
public class AnnotationHolder {
    private Set<Annotation> annotations = new HashSet<>(5);

    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<Annotation> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
    }

    public <T extends Annotation> T getAnnotation(final Class<T> tClass) {
        if (annotations == null) {
            return null;
        }

        List<Annotation> res = annotations.stream().filter(annotation -> annotation.annotationType().equals(tClass)).collect(Collectors.toList());

        return res == null || res.size() == 0 ? null : (T) res.get(0);
    }
}
