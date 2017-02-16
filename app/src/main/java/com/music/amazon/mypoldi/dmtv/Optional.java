package com.music.amazon.mypoldi.dmtv;

import java.util.NoSuchElementException;

public final class Optional<T> {

    private static final Optional<?> EMPTY = new Optional<>();

    private final T value;

    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? (Optional<T>) empty() : of(value);
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Optional)) {
            return false;
        }

        Optional<?> other = (Optional<?>) obj;
        return (value == null) ?
                (other.value == null) :
                value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    private Optional() {
        this.value = null;
    }

    private Optional(final T value) {
        if (value == null) {
            throw new NullPointerException();
        } else {
            this.value = value;
        }
    }
}
