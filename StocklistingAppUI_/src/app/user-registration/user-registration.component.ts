import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit, OnDestroy {
  user: User = new User();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    // Set background image for the registration page
    document.body.style.backgroundImage = "url('/assets/reg.jpg')"; // Ensure 'reg.jpg' is in your assets folder
    document.body.style.backgroundSize = "cover"; // Cover the entire body
    document.body.style.backgroundPosition = "center"; // Center the background image
    document.body.style.backgroundRepeat = "no-repeat"; // No repeat of the image
  }

  ngOnDestroy(): void {
    // Reset the body background image and color when the component is destroyed
    document.body.style.backgroundImage = '';
    document.body.style.backgroundColor = ''; // Reset to original color if needed
  }

  saveUser() {
    this.userService.saveUser(this.user).subscribe(
      data => {
        console.log(data);
        if (data != null) {
          alert("User Registered Successfully");
          // Navigate to home after registration
          this.router.navigate(['/']);
        }
      },
      error => {
        console.error(error);
        // Handle error
      }
    );
  }
}

