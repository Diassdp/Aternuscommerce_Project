package com.aplikasi.aternuscommerce.API.Response;

import java.util.List;

public class ShelfResponse<T> {
    List<T> result;
    public List<T> getResult() {
        return result;
    }
    public void setResult(List<T> result) {
        this.result = result;
    }
}
