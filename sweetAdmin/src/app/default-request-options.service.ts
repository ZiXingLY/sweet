/**
 * Created by zaq on 2017/5/19.
 */
import { Injectable } from '@angular/core';
import { BaseRequestOptions, RequestOptions } from '@angular/http';

@Injectable()
export class DefaultRequestOptions extends BaseRequestOptions {

    constructor() {
        super();

        // Set the default 'Content-Type' header
        // this.headers.set('Content-Type', 'application/x-www-form-urlencoded');
    }
}

export const requestOptionsProvider = { provide: RequestOptions, useClass: DefaultRequestOptions };
