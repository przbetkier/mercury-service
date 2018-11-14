import {Component, Input, OnInit} from '@angular/core';
import {Article} from '../../../model/article.model';
import {isNullOrUndefined} from 'util';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {

  @Input() article: Article;

  ngOnInit() {
    if (!this.hasAuthor()) {
      this.article.author = 'unknown';
    }
  }

  hasAuthor() {
    return !isNullOrUndefined(this.article.author);
  }

  navigateToArticle(url: string) {
    window.open(url, '_blank');
  }
}
