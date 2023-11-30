import { Component, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  constructor(private renderer: Renderer2) { }

  showToast() {
    this.renderer.removeClass(document.getElementById('toast-default'), 'hidden');
  }
}
