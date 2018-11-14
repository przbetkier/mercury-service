import {Component, OnInit} from '@angular/core';
import {NewsService} from '../../service/news.service';
import {Article} from '../../model/article.model';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {ComponentState} from '../../model/component-state';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {

  private category;
  pageState = new ComponentState();
  articles: Article[];

  constructor(private newsService: NewsService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getCategory();
    this.newsService.getNews('pl', this.category).subscribe(data => {
      this.articles = data.articles;
      this.pageState.onSuccess();
    }, error => {
      this.pageState.onErrorCode(error.status);
    });
  }

  private getCategory() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.category = params.get('category');
    });
  }

}
