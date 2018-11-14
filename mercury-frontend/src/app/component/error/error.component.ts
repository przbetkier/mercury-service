import {Component, Input} from '@angular/core';
import {ComponentError} from '../../model/component-error';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html'
})
export class ErrorComponent {

  @Input() error: ComponentError;
  ComponentError = ComponentError;
}
