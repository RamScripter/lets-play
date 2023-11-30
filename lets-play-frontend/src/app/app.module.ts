import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './modules/app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AdComponent } from './ad-components/ad/ad.component';
import { AdsListComponent } from './ad-components/ads-list/ads-list.component';
import { ScrollToTopComponent } from './scroll-to-top/scroll-to-top.component';
import { SingleAdComponent } from './ad-components/single-ad/single-ad.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './authentication/login/login.component';
import { SignupComponent } from './authentication/signup/signup.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AdComponent,
    AdsListComponent,
    ScrollToTopComponent,
    SingleAdComponent,
    SearchComponent,
    LoginComponent,
    SignupComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
