import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { NewsListComponent } from './component/news-list/news-list.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import {RouterModule} from '@angular/router';
import { ArticleComponent } from './component/news-list/article/article.component';
import {FormsModule} from '@angular/forms';
import { SpinnerComponent } from './component/common/spinner/spinner.component';
import { ErrorComponent } from './component/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    NewsListComponent,
    HomepageComponent,
    ArticleComponent,
    SpinnerComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      [
        {path: '', component: HomepageComponent},
        {path: 'news/:category', component: NewsListComponent}
      ]
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
