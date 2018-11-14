import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {NewsResponse} from '../model/news.response.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) {
  }

  public getNews(country: string, category: string): Observable<NewsResponse> {
    return this.http.get<NewsResponse>(`/news/${country}/${category}`);
  }
}
