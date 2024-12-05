import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { StockSearchComponent } from './stock-search/stock-search.component';
import { WishlistComponent } from './wishlist/wishlist.component';


const routes: Routes = [
 // { path: '', redirectTo: '/login', pathMatch: 'full' },
 { path: '', component: HomePageComponent },

  { path: 'login', component: UserLoginComponent },
  { path: 'register', component: UserRegistrationComponent },
  { path: 'search-stocks', component: StockSearchComponent },
  { path: 'towishlist', component: WishlistComponent }
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
