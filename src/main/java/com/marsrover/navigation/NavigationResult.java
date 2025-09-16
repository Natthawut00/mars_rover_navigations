package com.marsrover.navigation;

import com.google.gson.Gson;

public record NavigationResult(int x, int y, String direction, String status) {

    public String toJson() {
        return new Gson().toJson(this);
    }
}
