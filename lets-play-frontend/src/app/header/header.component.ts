import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  showDescription!: boolean;

  constructor(private router: Router) { }

  isHomeRoute(): boolean {
    return this.router.url === '/home';
  }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Obtenez la route actuelle après la fin de la navigation
        const currentRoute = this.router.url;
  
        // Vérifiez si la route est "/home"
        this.showDescription = currentRoute === '/home';
      }
    });

    console.log(this.showDescription);
  }

}
