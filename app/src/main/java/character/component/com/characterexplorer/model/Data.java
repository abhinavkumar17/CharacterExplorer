package character.component.com.characterexplorer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("total")
    private int total;
    @SerializedName("limit")
    private int limit;
    @SerializedName("results")
    private List<Results> results;
    @SerializedName("count")
    private int count;
    @SerializedName("offset")
    private int offset;

    public Data() {
    }

    public Data(int total, int limit, List<Results> results, int count, int offset) {
        this.total = total;
        this.limit = limit;
        this.results = results;
        this.count = count;
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
