package pl.szotaa.spotifyfetcher.enrichment;

public interface Enricher<T> {
    T enrich(T t);
}
