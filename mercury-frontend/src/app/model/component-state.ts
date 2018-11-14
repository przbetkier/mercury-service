import {ComponentError} from './component-error';
import {ComponentStateStatus} from './component-state-status';

export class ComponentState {

  state: ComponentStateStatus = ComponentStateStatus.LOADING;
  errorType: ComponentError;

  public onSuccess() {
    this.state = ComponentStateStatus.READY;
  }

  public onErrorCode(errorCode: number) {
    switch (errorCode) {
      case 500:
        this.onError(ComponentError.INTERNAL_SERVER_ERROR);
        break;
      case 503:
        this.onError(ComponentError.SERVICE_UNAVAILABLE);
        break;
      default:
        this.onError(ComponentError.GENERAL_ERROR);
    }
  }

  public isReady(): Boolean {
    return this.state === ComponentStateStatus.READY;
  }

  public isLoading(): Boolean {
    return this.state === ComponentStateStatus.LOADING;
  }

  public hasError(): Boolean {
    return this.state === ComponentStateStatus.ERROR;
  }

  private onError(error: ComponentError) {
    this.state = ComponentStateStatus.ERROR;
    this.errorType = error;
    return error;
  }
}

