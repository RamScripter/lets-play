import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdsListComponent } from '../ad-components/ads-list/ads-list.component';
import { SingleAdComponent } from '../ad-components/single-ad/single-ad.component';
import { SearchComponent } from '../search/search.component';
import { LoginComponent } from '../authentication/login/login.component';
import { SignupComponent } from '../authentication/signup/signup.component';
import { ProfileComponent } from '../user/profile/profile.component';

const routes: Routes = [
  { path: 'home', component: AdsListComponent },
  { path: 'ads/:id', component: SingleAdComponent },
  { path: 'search', component: SearchComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignupComponent},
  { path: 'profile', component: ProfileComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
