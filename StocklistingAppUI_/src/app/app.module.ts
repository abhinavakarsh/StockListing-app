import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserLoginComponent } from './user-login/user-login.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { StockSearchComponent } from './stock-search/stock-search.component';
import { MatSnackBarModule } from '@angular/material/snack-bar'; // Import MatSnackBarModule for notifications
import { MatMenuModule } from '@angular/material/menu'; // Import MatMenuModule for dropdown functionality
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog'; // Import MatDialogModule for dialog functionality

// Import Services
import { AuthService } from './auth.service';
import { UserService } from './user.service';
import { StockService } from './stock.service';
import { WishlistService } from './wishlist.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { WelcomeDialogComponent } from './welcome-dialog/welcome-dialog.component'; // Required for Material animations

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    UserRegistrationComponent,
    UserLoginComponent,
    StockSearchComponent,
    WishlistComponent,
    ConfirmDialogComponent,
    WelcomeDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatSnackBarModule, // Add MatSnackBarModule to imports for snack bar notifications
    MatMenuModule, // Add MatMenuModule for dropdown menu
    MatIconModule, // Add MatIconModule for icons
    MatDialogModule, // Add MatDialogModule for dialog functionality
    BrowserAnimationsModule // Add BrowserAnimationsModule for Angular Material animations
  ],
  providers: [
    AuthService, 
    UserService,
    StockService, // Provide StockService
    WishlistService // Provide WishlistService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
