import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { MatDialog } from '@angular/material/dialog';
import { WelcomeDialogComponent } from '../welcome-dialog/welcome-dialog.component'; // Import the dialog component

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog // Inject MatDialog
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      this.authService.login(loginData).subscribe({
        next: (response: any) => {
          // Store the token and username in local storage
          localStorage.setItem('token', response.token);
          localStorage.setItem('username', loginData.username);

          // Redirect to the protected resource
          this.router.navigate(['/search-stocks']);

          // Open the welcome dialog
          this.dialog.open(WelcomeDialogComponent);
        },
        error: (error) => {
          this.errorMessage = error.error.message || 'Login failed. Please try again.';
        }
      });
    }
  }
}
