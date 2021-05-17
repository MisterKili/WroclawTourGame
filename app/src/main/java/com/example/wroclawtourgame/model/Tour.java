package com.example.wroclawtourgame.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.wroclawtourgame.logic.TourWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tour implements Serializable {

    private String fileName;
    private String name;
    private String description;
    private String duration;
    private List<TourPoint> points;

    public Tour() {
        this.points = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TourPoint> getPoints() {
        return points;
    }

    void setPoints(List<TourPoint> points) {
        this.points = points;
    }

    public void addTourPoint(TourPoint point) {
        points.add(point);
    }

    public boolean hasPoints() {
        return !points.isEmpty();
    }

    public boolean isFinished() {
        if (!hasPoints()) {
            return false;
        }
        
        for (TourPoint point : points) {
            if (!point.isAnswered()) {
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<TourPoint> firstNotVisitedPoint() {
        for (TourPoint point: points) {
            if (!point.isAnswered()) {
                return Optional.of(point);
            }
        }

        return Optional.empty();
    }
}
