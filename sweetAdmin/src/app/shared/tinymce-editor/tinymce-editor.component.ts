import {Component, OnInit, OnDestroy, Output, EventEmitter, Input, AfterViewInit} from '@angular/core';
import {ProcessService} from '../process.service';

@Component({
    selector: 'app-tinymce-editor',
    template: '<div class="form-group"><textarea id="post_editor" class="form-control" rows="20" placeholder="请输入内容"></textarea></div>',
})

export class TinymceEditorComponent implements OnInit, OnDestroy, AfterViewInit {
    @Input() receiveContent: EventEmitter<any> = new EventEmitter();
    @Output() updateContent: EventEmitter<any> = new EventEmitter();
    public editor;
    public isEditorInit: boolean;
    public content: any;

    constructor(public processService: ProcessService) {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
        this.receiveContent.subscribe((data) => {
            if (data) {
                this.content = data;
                if (this.isEditorInit) {
                    tinymce.get('post_editor').setContent(data);
                }
            }
        });
        /**
         *  【非常重要】
         *  关于TinyMCE的完整文档，请查看这里https://www.tinymce.com/docs/
         */
        tinymce.init({
            selector: '#post_editor',
            language: 'zh_CN',
            placeholder: '',
            language_url: './assets/langs/zh_CN.js',  // site absolute URL
            skin_url: './assets/skins/lightgray',
            menubar: false,
            convert_urls: false,
            relative_urls: IMG_URL,
            plugins: [
                'advlist autolink lists link image charmap print preview hr anchor pagebreak',
                'searchreplace wordcount visualblocks visualchars code fullscreen',
                'insertdatetime media nonbreaking save table contextmenu directionality',
                'emoticons template paste textcolor colorpicker textpattern image imagetools codesample'
            ],
            toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent link ',
            toolbar2: 'image print forecolor backcolor preview',
            image_advtab: false, // 是否开启图片的高级选项
            codesample_content_css: './assets/css/prism.css',
            // 文件和图片上传相关的选项
            // file_browser_callback_types: 'image',
            file_picker_types: 'image',
            file_browser_callback: (field_name) => {
                const input = document.createElement('input');
                input.setAttribute('type', 'file');
                input.setAttribute('accept', 'image/*');
                input.click();
                input.onchange = () => {
                    const file = input.files[0];
                    const reader = new FileReader();
                    reader.readAsDataURL(file);
                    if (!reader) {
                        return;
                    }
                    reader.onload = () => {
                        this.processService.show();
                        this.processService.drawFrame(0);
                        const formData = new FormData();
                        formData.append('base64', reader.result);
                        formData.append('rootUrl',' ');
                        // formData.append('token', localStorage.token);
                        const xhr = new XMLHttpRequest();
                        xhr.upload.addEventListener('progress', (ev) => {
                            this.uploadProgress(ev);
                        }, false);
                        xhr.addEventListener('load', (ev) => (this.uploadComplete(ev, field_name)), false);
                        xhr.addEventListener('error', this.uploadFailed, false);
                        xhr.addEventListener('abort', this.uploadCanceled, false);
                        xhr.open('POST', ROOT_URL + 'article/uploadImage');
                        xhr.send(formData);
                    }
                }
            },
            init_instance_callback: (editor) => {
                this.isEditorInit = true;
                this.receiveContent.emit(this.content);
            },
            // 富文本内容改变后的事件
            setup: editor => {
                this.editor = editor;
                editor.on('change', () => {
                    const content = editor.getContent();
                    this.updateContent.emit(content);
                });
            }
        });
    }

    // 上传中 上传进度控制
    uploadProgress(evt) {
        this.processService.drawFrame(evt.loaded / evt.total * 100);
    }

    // 上传完成
    uploadComplete(evt, field_name) {
        (<HTMLInputElement>document.getElementById(field_name)).value = IMG_URL + JSON.parse(evt.target.responseText);
        console.log(evt.target.responseText);
        this.processService.drawFrame(100);
        setTimeout(() => {
            this.processService.hide();
        }, 200);
    }

    // 中止图片上传后
    uploadCanceled(evt) {
        console.log(evt);
    }

    // 上传失败
    uploadFailed(evt) {
        console.log(evt);
    }

    ngOnDestroy() {
        tinymce.remove(this.editor);
    }
}