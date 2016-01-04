package model;

@:final
class User {
    public function new() { }

    public var login:String;
    public var id:Int;
    public var avatar_url:String;
    public var gravatar_id:String;
    public var url:String;
    public var html_url:String;
    public var followers_url:String;
    public var following_url:String;
    public var gists_url:String;
    public var starred_url:String;
    public var subscriptions_url:String;
    public var organizations_url:String;
    public var repos_url:String;
    public var events_url:String;
    public var received_events_url:String;
    public var type:String;
    public var site_admin:Bool;
    public var name:String;
    public var company:String;
    public var location:String;
    public var email:String;
    public var followers:Int;
}
