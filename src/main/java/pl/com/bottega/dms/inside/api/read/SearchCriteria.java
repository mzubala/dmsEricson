package pl.com.bottega.dms.inside.api.read;

public abstract class SearchCriteria {

    private int page = 1, perPage = 25;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
