{{-- @author BenjaminDTS --}}
@extends('layouts.app')
@section('content')
    <ul class="list-group w-50 mx-auto">
        @foreach($frutas as $fruta)
            <li class="list-group-item">{{ $fruta }}</li>
        @endforeach
    </ul>
@endsection
