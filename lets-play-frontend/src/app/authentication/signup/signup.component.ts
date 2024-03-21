import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  hasError: boolean = false;
  error: String = '';
  passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;


  onSubmit(form: NgForm) {
    const password = form.value.password;

    if (!this.passwordRegex.test(password)){
      this.hasError = true;
      console.error('Invalid password')
      this.error = 'Le mot de passe doit contenir au moins 8 caractères, une lettre, un chiffre et un caractère spécial';
      return;
    }

    if (password !== form.value.confirmPassword) {
      this.hasError = true;
      console.error('Invalid password')
      this.error = 'Les mots de passe ne correspondent pas';
      return;
    }

    console.log(form.value);
    form.reset();
  }

}
